package ru.newtry.database.users

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

object Users: Table("projusers") {
    private val login = Users.varchar("login", 50)
    private val password = Users.varchar("password", 50)
    private val wishlist = Users.varchar("wishlist", 500)

    fun insert(UserDTO: UserDTO) {
        transaction {
            Users.insert {
                it[login] = UserDTO.login
                it[password] = UserDTO.password
                it[wishlist] = UserDTO.wishlist
            }
        }


    }

    fun fetchUser(login: String): UserDTO? {
        return try {
            transaction {
                val userModel = Users.select { Users.login.eq(login) }.single()
                UserDTO(
                    login = userModel[Users.login],
                    password = userModel[password],
                    wishlist = userModel[wishlist]
                )
            }
        } catch (e: Exception) {
            null
        }
    }

    fun addWish(wish: String, login: String): String {
        return transaction {
            val userModel = Users.select { Users.login.eq(login) }.single()
            var wl: String = userModel[wishlist]
            var num: Int = 1
            for (a in wl) {
                if (a.toString() == "\n") {
                    num += 1
                }
            }
            Users.update({ Users.login eq login }) {
                it[wishlist] = wl + "$num.$wish" + "\n"
                //govno error
            }
            val a = Users.select { Users.login.eq(login) }.single()
            a[wishlist]
        }
    }

    fun deleteWish(wishnum: String, login: String): String {
        return transaction {
            val userModel = Users.select { Users.login.eq(login) }.single()
            var wl1: String = userModel[wishlist]

            //var wl1 = "1.@\n2.@\n3.@\n4.@\n5.@\n6.@\n7.@\n8.@\n9.@\n10.@\n11.@\n12.@\n13.@\n14.@\n15.@\n"
            var k = wishnum.toInt() - 1
            var k2 = k + 1
            var iEnd = 0
            var iStart = 0
            var i_wl = -1

            //1..........................................................
            for (char in wl1) {
                if (wishnum == "1") {
                    iEnd = 0
                    break
                }
                i_wl++
                if (char.toString() == "\n" || wl1[i_wl + 1].toString() == wishnum) {
                    k--
                }
                iEnd++
                if (k <= 0) {
                    break
                }
            }
            //2...........................................................
            var fl2 = false
            for (char in wl1) {
                if (fl2) {
                    break
                }
                if (char.toString() == "\n") {
                    k2--
                }
                if (k2 == 0) {
                    fl2 = true
                }
                iStart++
            }

            //println("$iStart, ${wl1.count()}, $iEnd ")
            //print("...........................")
            var p1 = wl1.substring(0, iEnd)
            var p2 = wl1.substring(iStart, wl1.count())
            //print("$p1- p1, $p2 - p2")
            var wl_itog = (p1 + p2) // with wrong nums
            //println(".................")
            //println(wl_itog)
            //print("-------------------------")
            //var wl_itog = "1.Domik\n2.NewCar\n3.Machinka\n"
            var strs = wl_itog.split("\n").toTypedArray()
            println(".................................")
            var wl = charArrayOf()
            var i2: Char = '1'
            var L = 0
            var place = 0
            var S = 0
            var str = ""
            var k_n = 0
            k_n = wishnum.toInt() - 1
            var i = -1
            var a:String = (wishnum.toInt()).toString()
            var b:String = (a.toInt()-2).toString()
            var wl_itog_2 = wl_itog
            println("$wl_itog_2 .-.-.-.-.---.-.-.-.")
            for(char in wl_itog){
                if(char == '\n'){
                    k_n--
                }
                if(k_n <=0){
                    if(char == '\n'){
                        a = (a.toInt()+1).toString()
                        b = (a.toInt()-1).toString()
                        print("-------------$a, $b-------------, $i-----")
                        wl_itog_2 = wl_itog_2.replace("\n$a","\n$b" )
                        println("$wl_itog_2, wlitog")
                    }
                }
            }
            println(".,//,./,./,./,./,,././,/,/./,./,./,./,.")
            var g = charArrayOf()
            for(char in wl_itog_2){
                g += char
            }
            g[0] = '1'

            print(wl_itog_2)
            var wl_itog_3 = ""
            for(char in g){
                wl_itog_3 += char
            }


            println(",./,.//.,./,/.,/,/./.,/.,/.,.//./.,/,./.,/.,/,./,./,./,.,/.,/.,/./,.,/./,.,/.")


            Users.update({ Users.login eq login }) {
                it[wishlist] = wl_itog_3
            }
            val bbb = Users.select { Users.login.eq(login) }.single()

            bbb[wishlist]


        }
    }
}