const express = require('express')
const db = require('../db')
const utils = require('../utils')
const multer = require('multer')
const upload = multer({dest: 'thumbnails/'})

const router = express.Router()

router.get('/', (request, response) => {
    const connection = db.connect()
    const statement = `select * from Product`
    connection.query(statement, (error, products) => {
        connection.end()
        response.send(utils.createResult(error, products))
    })
})

router.post('/', (request, response) => {
    const {title, description, price, thumbnail, categoryId} = request.body
    const connection = db.connect()
    const statement = `insert into Product (title, description, price, thumbnail, categoryId) values ('${title}', '${description}', '${price}', '${thumbnail}', '${categoryId}')`
    connection.query(statement, (error, products) => {
        connection.end()
        response.send(utils.createResult(error, products))
    })
})

router.post('/with-image', upload.single('thumbnail'), (request, response) => {
    const {title, description, price, categoryId} = request.body
    const thumbnail = request.file.filename
    
    const connection = db.connect()
    const statement = `insert into Product (title, description, price, thumbnail, categoryId) values ('${title}', '${description}', '${price}', '${thumbnail}', '${categoryId}')`
    connection.query(statement, (error, products) => {
        connection.end()
        response.send(utils.createResult(error, products))
    })
})

module.exports = router