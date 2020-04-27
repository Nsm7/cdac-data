const express = require('express')
const db = require('../db')
const utils = require('../utils')
const cryptoJs = require('crypto-js')

const router = express.Router()

router.post('/signup', (request, response) => {
    const {email, password, username, full_name} = request.body
    const encryptedPassword = cryptoJs.MD5(password)
    const connection = db.connect()
    const statement = `insert into User (username, full_name, password, email) values ('${username}', '${full_name}', '${encryptedPassword}', '${email}')`
    connection.query(statement, (error, data) => {
        connection.end()
        response.send(utils.createResult(error, data))
    })
})

router.post('/signin', (request, response) => {
    const {email, password} = request.body
    const encryptedPassword = cryptoJs.MD5(password)
    const connection = db.connect()
    const statement = `select * from User where email = '${email}' and password = '${encryptedPassword}'`
    connection.query(statement, (error, users) => {
        connection.end()

        if (users.length == 0) {
            response.send({
                status: 'error',
                error: 'user does not exist'
            })
        } else {
            const user = users[0]
            response.send({
                status: 'success',
                data: {
                    id: user.id,
                    full_name: user.full_name,
                    username: user.username,
                    email: user.email
                }
            })
        }
    })
})

module.exports = router
