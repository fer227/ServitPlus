const mongoose = require('mongoose');

mongoose.connect('mongodb://localhost:27017/Servit', {useNewUrlParser: true, useUnifiedTopology: true}, 
(err) => {
    if (!err) {
        console.log('Conexión exitosa con la base de datos')
    }
    else {
        console.log('Error al intentar conectar con la base de datos: '+ err)
    }
});

module.exports = mongoose;