const fcm = require('fcm-node')

const FCM = new fcm('AIzaSyCaD026HoxDtBjLhhJEEpoQHw2w7QhS_Yk')

const message = {
    to: 'cizb4DSlrMI:APA91bFVeSZHNlQ-FxUoO_BiHz3l_mukOeFfkRchLIYQwuNPqrMTns4_Ho_Pp7YJ59H00UCirHde3XcdfZvbCWr8o3trHg7vSfCFE82A7IKGL5P6TkqL3w0f-hYMIKT1tPY6e3WG7AOl', 
    notification: {
        title: 'new offer', 
        body: 'Our Application has launched a new offer. please go..........' 
    }
}

FCM.send(message, (error, response) => {
    console.log('error: ', error)
    console.log('response: ', response)
})