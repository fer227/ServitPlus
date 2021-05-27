const mongoose = require("../db");

const schema = new mongoose.Schema(
    {
        producto: mongoose.Schema.Types.ObjectId,
        cantidad: Number
    }
);

module.exports = mongoose.model("Carrito", schema);