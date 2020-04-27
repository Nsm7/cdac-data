const express = require('express')
const multer = require('multer')
const upload = multer({dest: 'thumbnails'})

const app = express()
app.use(function(req, res, next) {
    res.header("Access-Control-Allow-Origin", "*");
    res.header("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE");
    res.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
    next();
});
app.use(express.static('thumbnails'));

app.get('/', (request, response) => {
    const result = { status: 'success' }
    response.send(result)
})

app.post('/profile', upload.single('photo'), (request, response) => {
    const result = { status: 'success' }
    response.send(result)
})


app.listen(4000, '0.0.0.0', () => {
    console.log('server started  on port 4000')
})