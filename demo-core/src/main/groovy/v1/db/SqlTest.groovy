package v1.db

import groovy.sql.Sql

/**
 * Created by BG244210 on 2016/7/8.
 */
def url = 'jdbc:mysql://10.45.8.124:3306/lbs?useUnicode=true&characterEncoding=UTF-8'
def user = 'lbsuser'
def pass = 'pass'
def driverName = 'com.mysql.jdbc.Driver'

def db = Sql.newInstance url, user, pass, driverName

printf "执行查询语句\n"
printf '|%-8s|%-11s|%-30s|%-8s|%-9s|\n', 'id', 'phone', 'register_date', 'city_id', 'is_delete'
db.eachRow('select * from practice_test', {
    result -> printf '|%-8s|%-11s|%-30s|%-8s|%-8s|\n', result.getAt(0), result.getAt(1), result.getAt(2), result.getAt(3), result.getAt(4)
})

printf "\n\n执行修改类语句\n"
list = ['13989455787', new Date(), 123, 1]
List<List<Object>> list1 = db.executeInsert 'INSERT  into practice_test(phone, register_date, city_id,is_delete) VALUES (?,?,?,? )', list
assert list1
db.close()

