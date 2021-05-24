const mongoose = require("../db");

const schema = new mongoose.Schema({
    nombre: String,
    precio: Number,
    descripcion: String,
    ingredientes: [{
        nombre: String
    }],
    alergias: [{
        numero: Number
    }],
    categoria: mongoose.Schema.Types.ObjectId
});

module.exports = mongoose.model("Producto", schema);