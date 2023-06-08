var mqtt = require("mqtt");
var client1 = mqtt.connect("mqtt://broker.hivemq.com");

const { Client } = require("pg");
const credentials = {
  user: "postgres",
  host: "localhost",
  database: "farm",
  password: "Nhn@300102",
  port: 5432,
};
const client = new Client(credentials);
client.connect(); // gets connection
const connectDb = async () => {
  try {
    await client.connect();
    const res = await client.query("SELECT * from sensor");
    //console.log(res)
    console.log(res.rows[0]["timestamp"]);
    await client.end();
  } catch (error) {
    console.log(error);
  }
};

// connectDb();

const insertSensor = async (
  timestamp,
  temperature,
  luminosity,
  airHumidity,
  soilHumidity
) => {
  try {
    await client.query(
      "INSERT INTO sensor (timestamp, temperature, luminosity, air_humidity, soil_humidity) VALUES ($1, $2, $3, $4, $5)",
      [timestamp, temperature, luminosity, airHumidity, soilHumidity]
    ); // sends queries
    return true;
  } catch (error) {
    console.error(error.stack);
    return false;
  }
  //  finally {
  //   await client.end(); // closes connection
  // }
};
client1.on("connect", function () {
  setInterval(function () {
    var currentdate = new Date();
    var datetime =
      currentdate.getDay() +
      "-" +
      currentdate.getMonth() +
      "-" +
      currentdate.getFullYear() +
      " " +
      currentdate.getHours() +
      ":" +
      currentdate.getMinutes() +
      ":" +
      currentdate.getSeconds();
    var temperature;
    var luminosity;
    var airHumidity;
    var soilHumidity;
    insertSensor(
      datetime,
      (temperature = Math.floor(Math.random() * 50)),
      (luminosity = Math.floor(Math.random() * 50)),
      (airHumidity = Math.floor(Math.random() * 100)),
      (soilHumidity = Math.floor(Math.random() * 100))
    ).then((result) => {
      if (result) {
        console.log("Sensor data published successfully");
      }
      client1.publish(
        "my",
        "Timestamp: " +
          datetime +
          "\nTemperature: " +
          temperature.toString() +
          "\nLuminosity: " +
          luminosity.toString() +
          " lux" +
          "\nAir Humidity: " +
          airHumidity.toString() +
          "%" +
          "\nSoil Humidity: " +
          soilHumidity.toString() +
          "%"
      );

      if (temperature < 27) {
        client1.publish("my", "Turn off the fan!");
      } else if (temperature > 30) {
        client1.publish("my", "Turn on the fan!");
      }

      if (soilHumidity < 50) {
        client1.publish("my", "Turn off the water pump!");
      } else if (temperature > 60) {
        client1.publish("my", "Turn on the water pump!");
      }

      if (luminosity < 30) {
        client1.publish("my", "Turn on the light!\n\n");
      } else {
        client1.publish("my", "Turn off the light!\n\n");
      }
    });
    queryDb("sensor");
  }, 3000);
});

const queryDb = async (tab) => {
  try {
    const query = "SELECT * from " + tab;
    //await client.connect();
    const res = await client.query(query);
    var temp = 0;
    for (i = 0; i < res.rowCount; i++) {
      temp += parseInt(res.rows[i].temperature);
    }
    console.log("Average Temparature: " + temp / res.rowCount);
    var luminosity = 0;
    for (i = 0; i < res.rowCount; i++) {
      luminosity += parseInt(res.rows[i].luminosity);
    }
    console.log("Average luminosity: " + luminosity / res.rowCount);

    var soil_humidity = 0;
    for (i = 0; i < res.rowCount; i++) {
      soil_humidity += parseInt(res.rows[i].soil_humidity);
    }
    console.log("Average soil_humidity: " + soil_humidity / res.rowCount);
    //await client.end();
  } catch (error) {
    console.log(error);
  }
};
//queryDb("sensor");