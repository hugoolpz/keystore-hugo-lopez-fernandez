"use strict";
var __awaiter = (this && this.__awaiter) || function (thisArg, _arguments, P, generator) {
    function adopt(value) { return value instanceof P ? value : new P(function (resolve) { resolve(value); }); }
    return new (P || (P = Promise))(function (resolve, reject) {
        function fulfilled(value) { try { step(generator.next(value)); } catch (e) { reject(e); } }
        function rejected(value) { try { step(generator["throw"](value)); } catch (e) { reject(e); } }
        function step(result) { result.done ? resolve(result.value) : adopt(result.value).then(fulfilled, rejected); }
        step((generator = generator.apply(thisArg, _arguments || [])).next());
    });
};
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
const express_1 = __importDefault(require("express"));
const mongoose_1 = __importDefault(require("mongoose"));
const cors_1 = __importDefault(require("cors"));
const dato_1 = __importDefault(require("../routes/dato"));
class Servidor {
    constructor() {
        this.rutasApi = {
            datosPrivados: "/api/datosPrivados/",
        };
        this.app = (0, express_1.default)();
        //Usamos la variable de entorno PORT y si es null, ponemos 8080
        this.port = "3000";
        //Nos intentamos conectar a la BD
        this.establecerConexionBD();
        //Activamos los middlewares
        this.middlewares();
        //Definimos rutas
        this.routes();
    }
    establecerConexionBD() {
        return __awaiter(this, void 0, void 0, function* () {
            mongoose_1.default
                .connect(process.env.MONGO_URL)
                .then(() => {
                console.log("Éxito al conectar a Mongo");
            })
                .catch((error) => {
                console.log("Error al conectar a Mongo" + error);
            });
            mongoose_1.default.set("debug", true);
        });
    }
    middlewares() {
        //CORS
        this.app.use((0, cors_1.default)());
        //Parseo del body
        this.app.use(express_1.default.json());
        //Codificar url
        this.app.use(express_1.default.urlencoded({ extended: true }));
    }
    routes() {
        this.app.use(this.rutasApi.datosPrivados, dato_1.default);
    }
    listen() {
        this.app.listen(this.port, () => {
            console.log("El servidor activo en puerto " + this.port);
        });
    }
}
//Exportamos la clase
exports.default = Servidor;
//# sourceMappingURL=server.js.map