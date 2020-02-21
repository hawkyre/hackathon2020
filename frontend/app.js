const express = require('express');
const bodyParser = require('body-parser');

const app = express();

app.set('view engine', 'ejs');
app.use(express.static('public'));

// Get requests

app.get('/', (req, res) => {
    res.render("home", {imgurl: "/img/mockup.jpg"});
})

// Post requests



// Listen

app.listen(3000, function() {
    console.log("Server started on port 3000");
})