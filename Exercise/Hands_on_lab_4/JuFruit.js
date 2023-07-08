const pg = require('pg')
const {Pool} = require('pg');
var express = require('express');
var app = express();


const pool = new Pool({
    user: "postgres",
    host: "localhost",
    database: "postgres",
    password: "Nhn@300102",
    port: 5432,
    max: 20,
    idleTimeoutMillis: 30000,
    connectionTimeoutMillis: 2000,
    })
    //connect
    // await pool.connect();
    app.get('/', function(req, res){
    res.send('Welcome to JuFruit');
    });

    app.listen(5001, function(){
        console.log('Server is listening at port 5001...');
        });
    app.get('/get/:barcode', async (req, res) => {
        var strData = req.params.barcode;
            const result = await pool.query("SELECT * FROM product WHERE barcode = $1",[strData])
            console.log(result.rows)
            res.send(result.rows)
        });
        

