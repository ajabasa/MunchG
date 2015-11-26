var express = require('express'),
    app = express();
var mongodb = require('mongodb');

var Mongolient = mongodb.MongoClient;

app.get('/', function(req, res) {
  res.send("Hello from Express");
});

app.listen(3000, function() {
  console.log("Server ready. Listening on port 3000");
});

var url = require("url");
var queryString = requrie("queryString");



app.get('/munchPost', function(req,res)) {

});
