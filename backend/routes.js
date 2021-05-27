const Router = require('@koa/router');
const Carrito = require('./models/Carrito');
const router = new Router();
const Categoria = require("./models/Categoria");
const Pedido = require("./models/Pedido");
const Producto = require("./models/Producto");

/*
//Ejemplo de función con promesa
function getCategorias(){
    return new Promise((resolve, reject) => {
        Categoria.find({}, function(error, docs){
            if(error){
                reject(error);
            }
            else{
                resolve(docs);
            }
        })
    })
}*/

router.get('/categorias', async (ctx) => {   
    await Categoria.find({}).then((data) => {
        ctx.body = data
        ctx.status = 200;
    });
});

router.get('/productos/:id', async (ctx) => {   
    await Producto.find({categoria: ctx.params.id}).then((data) => {
        ctx.body = data
        ctx.status = 200;
    });
});

router.delete('/carrito/:id', async (ctx) => {   
    await Carrito.findByIdAndDelete({_id: ctx.params.id}).then((data)=>{
        ctx.body = {msg : "Eliminado correctamente del carrito"};
        ctx.status = 200;
    });
});

router.delete('/carrito', async (ctx) => {   
    await Carrito.deleteMany({}).then((data)=>{
        ctx.body = {msg : "Carrito vaciado"};
        ctx.status = 200;
    });
});

router.delete('/pedido', async (ctx) => {   
    await Pedido.deleteMany({}).then((data)=>{
        ctx.body = {msg : "Pedido vaciado"};
        ctx.status = 200;
    });
});

async function getProducto(id){
    producto = await Producto.findById(id);
    return producto
}

router.get('/pedido', async (ctx) => {   
    await Pedido.find({}).then(async (data) => {
        //hacer una copia del objeto
        var misDatos = JSON.parse(JSON.stringify(data));
        for(var i = 0; i < data.length; i++){
            producto = await getProducto(data[i]['producto']);
            misDatos[i].precio = producto.precio
            misDatos[i].nombre = producto.nombre
        }
        ctx.body = misDatos
        ctx.status = 200;
    });
});

router.get('/carrito', async (ctx) => {   
    await Carrito.find({}).then(async (data) => {
        //hacer una copia del objeto
        var misDatos = JSON.parse(JSON.stringify(data));
        for(var i = 0; i < data.length; i++){
            producto = await getProducto(data[i]['producto']);
            misDatos[i].precio = producto.precio;
            misDatos[i].nombre = producto.nombre;
        }
        ctx.body = misDatos
        ctx.status = 200;
    });
});

router.post('/pedido', async (ctx) => {
    body = ctx.request.body;

    for(var i = 0; i < body.length; i++){
        var actual = body[i];
        productoYaPedido = "";
        await Pedido.find({producto: actual['producto']}).then((data) => {
            productoYaPedido = data
        });
    
        if(productoYaPedido.length == 0){
            const nuevoPedido = new Pedido({
                producto: actual['producto'],
                cantidad: actual['cantidad']
            });
            nuevoPedido.save()
        }
        else{
            cantidad = productoYaPedido[0]['cantidad'];
            await Pedido.findOneAndUpdate({producto: actual['producto']}, {cantidad: actual['cantidad']*1 + cantidad});
            
        }
    }
    ctx.body = {msg: "Pedido recibido"};
    ctx.status = 201;
});

router.post('/carrito', async (ctx) => {
    body = ctx.request.body;

    for(var i = 0; i < body.length; i++){
        var actual = body[i];
        productoYaCarrito = "";
        await Carrito.find({producto: actual['producto']}).then((data) => {
            productoYaCarrito = data
        });
    
        if(productoYaCarrito.length == 0){
            const nuevoElementoCarrito = new Carrito({
                producto: actual['producto'],
                cantidad: actual['cantidad']
            });
            nuevoElementoCarrito.save()
        }
        else{
            cantidad = productoYaCarrito[0]['cantidad'];
            await Carrito.findOneAndUpdate({producto: actual['producto']}, {cantidad: actual['cantidad']*1 + cantidad});
            
        }
    }
    ctx.body = {msg: "Carrito actualizado"};
    ctx.status = 201;
});

router.post('/inicializardb', (ctx) => {
    const catBebidas = new Categoria({
        nombre: "Bebidas",
      });
    catBebidas.save();

    const catPrimeros = new Categoria({
        nombre: "Primeros",
      });
    catPrimeros.save();

    const prodCerveza = new Producto({
        nombre: "Cerveza",
        precio: 2.3,
        alergias: [{
            numero: 5
        }],
        categoria: catBebidas._id       
    });
    prodCerveza.save();

    const prodTinto = new Producto({
        nombre: "Tinto de verano",
        precio: 2,
        categoria: catBebidas._id       
    });
    prodTinto.save();

    const prodCola = new Producto({
        nombre: "Coca Cola",
        precio: 2,
        categoria: catBebidas._id       
    });
    prodCola.save();

    const prodFritura = new Producto({
        nombre: "Fritura de pescado",
        precio: 20.50,
        descripcion: "Selección variada de pescado frito. Ideal para compartir en familia.",
        ingredientes: [{
            nombre: "Boquerones"
        },
        {
            nombre: "Calamares"
        },
        {
            nombre: "Merluza"
        },
        {
            nombre: "Cazón"
        }
        ],
        alergias: [{
            numero: 11
        },
        {
            numero: 12
        }
        ],
        categoria: catPrimeros._id       
    });
    prodFritura.save();

    const prodHamburguesa = new Producto({
        nombre: "Hamburguesa",
        precio: 15.99,
        descripcion: "Prueba nuestra famosa hamburguesa.",
        ingredientes: [{
            nombre: "Carne de cerdo"
        },
        {
            nombre: "Lechuga"
        },
        {
            nombre: "Huevo"
        },
        {
            nombre: "Mostaza"
        },
        {
            nombre: "Queso"
        }
        ],
        alergias: [{
            numero: 1
        },
        {
            numero: 8
        },
        {
            numero: 5
        }
        ],
        categoria: catPrimeros._id       
    });
    prodHamburguesa.save();

    const prodCesar= new Producto({
        nombre: "Ensalada César",
        precio: 17,
        ingredientes: [{
            nombre: "Pollo empanado"
        },
        {
            nombre: "Lechuga"
        },
        {
            nombre: "Tomate"
        },
        {
            nombre: "Atún"
        },
        {
            nombre: "Cebolla"
        }
        ],
        alergias: [{
            numero: 12
        }
        ],
        categoria: catPrimeros._id       
    });
    prodCesar.save();

    const prodSolomillo = new Producto({
        nombre: "Solomillo a la brasa",
        precio: 26,
        descripcion: "Jugoso solomillo hecho de forma natural en nuestra brasa.",
        categoria: catPrimeros._id       
    });
    prodSolomillo.save();

    ctx.status = 201;
    ctx.body = {msg : 'Base de datos inicializada'};
});

module.exports = router;