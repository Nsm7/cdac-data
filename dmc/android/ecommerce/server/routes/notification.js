const express = require('express')
const FCM = require('fcm-node')
const fcm = new FCM('AIzaSyCaD026HoxDtBjLhhJEEpoQHw2w7QhS_Yk')
const utils = require('../utils')
const db = require('../db')

const router = express.Router()

router.post('/', (request, response) => {
    const {title, body} = request.body
    console.log(request.body)

    const statement = `select device_token from user`
    const connection = db.createConnection()
    connection.query(statement, (error, tokens) => {
        connection.end()

        if (error) {
            response.send(utils.createResult(error, tokens))
        } else {
            for (let index = 0; index < tokens.length; index++) {
                const token = tokens[index];

                console.log('token: ', token)
                const message = {
                    to: token['device_token'],
                    notification: {
                        title: title, 
                        body: body 
                    }
                }
            
                fcm.send(message, (error, info) => {
                    response.send(utils.createResult(error, info))
                })
            }
        }
    })
    
})

module.exports = router