const { Client } = require("pg");
const credentials = {
  user: "postgres",
  host: "localhost",
  database: "farm",
  password: "Nhn@300102",
  port: 5432,
};

const client = new Client(credentials);
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
    await client.connect(); // gets connection
    await client.query(
      "INSERT INTO sensor (timestamp, temperature, luminosity, air_humidity, soil_humidity) VALUES ($1, $2, $3, $4, $5)",
      [timestamp, temperature, luminosity, airHumidity, soilHumidity]
    ); // sends queries
    return true;
  } catch (error) {
    console.error(error.stack);
    return false;
  } finally {
    await client.end(); // closes connection
  }
};

// Only need to insert 1 time
// insertSensor("1999-08-05 04:05:07", 25, 15, 45, 65).then((result) => {
//   if (result) {
//     console.log("data inserted");
//   }
// });

const queryDb = async (tab, val) => {
  try {
    const query = "SELECT * from " + tab + " WHERE temperature = $1";
    const values = [val];
    await client.connect();
    const res = await client.query(query, values);
    console.log(res.rows[0]);
    await client.end();
  } catch (error) {
    console.log(error);
  }
};

queryDb('sensor', 25)