const express = require('express');
const bodyParser = require('body-parser');
const mongoose = require('mongoose');

const app = express();

app.set('view engine', 'ejs');
app.use(express.static('public'));
app.use(bodyParser.urlencoded({ extended: true }));

const url = 'mongodb://localhost:27017/';

// Get requests

app.get('/', (req, res) => {
    let events = [
        {
            title: 'UPV',
            date: '26/02/2020',
            photoName: 'poli'
        },
        {
            title: 'Viveros',
            date: '01/03/2020',
            photoName: 'viveros'
        },
        {
            title: 'Río Turia',
            date: '06/03/2020',
            photoName: 'rio'
        }
    ]
    res.render("home", {imgurl: "/img/mockup.jpg", events: events});
})

app.get('/login', (req, res) => {    
    res.render("login", {});
});

app.get('/register', (req, res) => {
    res.render("register", {});
});

// Post requests

app.post("/login", (req, res) => {
    console.log(req.body.email);
    res.redirect("/");
})

// Listen

app.listen(3000, function() {
    console.log("Server started on port 3000");
})