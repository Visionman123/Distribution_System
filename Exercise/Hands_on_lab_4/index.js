var express = require('express');
var app = express();
app.get('/', function(req, res){
res.send('Hello World!');
});
app.listen(5000, function(){
console.log('Server is listening at port 5000...');
});
app.get('/getPara/:para', function(req, res){
    var strData = req.params.para;
    // var strData1 = req.params.para1;
    // var strData2 = req.params.para1;
    // var strData3 = req.params.para1;
    res.send('Your parameter is: ' + strData + "VNĐ is " + strData*0.000042 + "USD");
    });

var bodyParser = require('body-parser');
app.use(bodyParser.json()); 
app.use(bodyParser.urlencoded({ extended: true })); 
app.post('/postPara', function(req, res){
var strData = req.body.para;
var strData1 = req.body.para1;
res.send('Your parameter is: ' + strData + " and " + strData1);
});0