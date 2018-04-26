import kotlin.system.exitProcess

var strings = mutableMapOf<String, String>()

fun main(vararg args: String) {
    var go = true
    println("----------------------------------------")
    println("!! enter 'help' to get help !!")
    println("----------------------------------------")
    while(go) {
        print("->    ")
        var s = readLine()!!
        if (s == null) {
            println("unknown command")
            println("enter 'help' to get help")
            println("----------------------------------------")
        } else {
            var all = s.split(" ")
            when (all[0]) {
                "exit"   -> exitProcess(0)
                "add"    -> add(s)
                "delete" -> delete(s)
                "find"   -> find(s)
                "help"   -> help()
                else     -> println("----------------------------------------" + "\n" +"unknown command" + "\n" + "enter 'help' to get help" + "\n" + "----------------------------------------")
            }
        }
    }
}

fun add(all: String) {
    var key = String()
    var string = String()
    var q = 0
    for (i in 4..all.length - 1) {
        if (all[i] == '|') {
            q++
        }else if (q == 0) {
            key += all[i]
        } else {
            string += all[i]
        }
    }
    strings.put(key, string)
    println("-----------successfully added-----------")
}

fun delete(all: String) {
    var key = String()
    var string = String()
    var q = 0
    for (i in 7..all.length - 1) {
        if (all[i] == '|') {
            q++
        }else if (q == 0) {
            key += all[i]
        } else {
            string += all[i]
        }
    }
    if (string == "$") {
        strings.remove(key)
    } else if (key == "$") {
        for (i in strings) {
            var str = i.value
            if (string == str) {
                strings.remove(i.key)
            }
        }
    } else strings.remove(key, string)
    println("----------successfully deleted----------")
}

fun find(all: String) {
    var key = String()
    var string = String()
    var q = 0
    for (i in 5..all.length - 1) {
        if (all[i] == '|') {
            q++
        }else if (q == 0) {
            key += all[i]
        } else {
            string += all[i]
        }
    }
    q = 0
    if (string == "$") {
        for (i in strings) {
            var str = i.key
            if (key in str) {
                print(">>    ")
                println(i.key+" | "+i.value)
                q++
            }
        }
    } else if (key == "$") {
        for (i in strings) {
            var str = i.value
            if (string in str) {
                print(">>    ")
                println(i.key+" | "+i.value)
                q++
            }
        }
    } else {
        for (i in strings) {
            var str = i.value
            var str2 = i.key
            if (string in str && key in str2) {
                print(">>    ")
                println(i.key+" | "+i.value)
                q++
            }
        }
    }
    if (q != 0) {
        println("-----------successfully found-----------")
    } else println("------------Error: Not found------------")
}

fun help() {
    println("----------------------------------------")
    println("'exit' - shut down the program" + '\n' +
            "'add key|string' - add a string by key. Key and string can contain spaces" + '\n'+
            "'find key|string' - search for pairs by a fragment of the string and/or key. You can 'find $|string' or 'find key|$'"+ '\n'+
            "'delete key|string' - delete a pair by key and/or string. You can 'delete $|string' or 'delete key|$'")
    println("----------------------------------------")
}