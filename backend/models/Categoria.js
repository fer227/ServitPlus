const mongoose = require("../db");

const schema = new mongoose.Schema({
    nombre: String,
});

module.exports = mongoose.model("Categoria", schema);