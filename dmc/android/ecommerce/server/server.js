const express = require('express')
const routerUser = require('./routes/user')
const routerProduct = require('./routes/product')
const routerCategory = require('./routes/category')
const routerNotification = require('./routes/notification')

const app = express()
app.use(function(req, res, next) {
    res.header("Access-Control-Allow-Origin", "*"); 
    res.header("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE"); 
    res.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
    next();
});
app.use(express.json())
app.use('/user', routerUser)
app.use('/product', routerProduct)
app.use('/category', routerCategory)
app.use('/notification', routerNotification)

app.use(express.static('images'))

app.listen(4000, '0.0.0.0', () => {
    console.log('server started  on port 4000')
})