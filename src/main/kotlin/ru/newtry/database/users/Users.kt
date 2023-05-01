package ru.newtry.database.users

import org.jetbrains.exposed.sql.*
import ru.newtry.database.users.*
import org.jetbrains.exposed.sql.transactions.transaction
import java.lang.Exception.*

object Users: Table("projusers") {
    private val login = Users.varchar("login", 50)
    private val password = Users.varchar("password", 50)
    private val wishlist = Users.varchar("wishlist", 500)

    fun insert(UserDTO:UserDTO){
            transaction {
                Users.insert {
                    it[login] = UserDTO.login
                    it[password] = UserDTO.password
                    it[wishlist] = UserDTO.wishlist
                }
            }



    }
    fun fetchUser(login:String):UserDTO?{
        return try{
            transaction {
                val userModel = Users.select { Users.login.eq(login) }.single()
                UserDTO(
                    login = userModel[Users.login],
                    password = userModel[password],
                    wishlist = userModel[wishlist]
                )
            }
        } catch (e:Exception){
            null
        }
    }
    fun addWish(wish:String, login: String):String{
            transaction {
                val userModel = Users.select { Users.login.eq(login) }.single()
                var wl:String = userModel[wishlist]
                var num:Int = 1
                for(a in wl){
                    if (a.toString() == "\n"){
                        num += 1
                    }
                }
                Users.update ({ Users.login eq login }) {
                    it[wishlist] = wl + "$num.$wish" + "\n"
                }
            }
        val a = Users.select { Users.login.eq(login) }.single()

        return a[wishlist];
    }

    fun deleteWish(wishnum:String, login:String):String{
        transaction {
            val userModel = Users.select { Users.login.eq(login) }.single()
            var wl1:String = userModel[wishlist]

            //var wl1 = "1.\n2.\n3."
            var k = wishnum.toInt() - 1
            var k2 = k +1
            var iEnd = 0
            var iStart = 0
            var i_wl = -1

            //1..........................................................
            for(char in wl1){
                if(wishnum == "1"){
                    iEnd = 0
                    break
                }
                i_wl++
                if (char.toString() == "\n" || wl1[i_wl+1].toString() == wishnum){
                    k--
                }
                iEnd++
                if (k <= 0){
                    break
                }
            }
            //2...........................................................
            var fl2 = false
            for(char in wl1){
                if (fl2){
                    break
                }
                if (char.toString() == "\n"){
                    k2--
                }
                if (k2 == 0){
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

            var wl = charArrayOf()
            var i2:Char = '1'
            var L = 0
            var place = 0
            var S = 0
            for(i in strs){
                print("\n")
                for(el in i){
                    wl += el
                }
                wl += '\n'

                L = i.toString().length + 1
                S = wl.size
                place = S - L
                if (place == S){
                    break
                }
                //println( i2)
                wl[place] = i2
                i2 = (i2.toInt() + 1).toChar()
            }
            //wl[S-1] = ' '
            var s = ""
            var i_last = 0

            for(els in wl){
                i_last++
                //if (i_last == S){
                 //   break
                //}
                s += els.toString()
            }
            var s2 = ""
            var a = s.count()
            for(el in 0..a-2){
                s2+= s[el].toString()
            }

            Users.update ({ Users.login eq login }) {
                it[wishlist] = s2
            }
        println("////////////////////")
            //delete last symbol
        println(s2)

        }
        val a = Users.select { Users.login.eq(login) }.single()

        return a[wishlist];
    }

}