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
exports.deleteDatoPrivado = exports.putDatoPrivado = exports.postDatoPrivado = exports.getDatoPrivado = exports.getDatosPrivados = void 0;
const dato_1 = __importDefault(require("../models/dato"));
const mongoose_1 = __importDefault(require("mongoose"));
const getDatosPrivados = (req, res) => __awaiter(void 0, void 0, void 0, function* () {
    const { uid } = req.params;
    yield dato_1.default.find()
        .where("idUsuario")
        .equals(uid)
        .exec()
        .then((resultados) => {
        return res.status(200).json({
            exito: true,
            datos: resultados,
        });
    })
        .catch((error) => {
        return res.status(500).json({
            exito: false,
            error,
        });
    });
});
exports.getDatosPrivados = getDatosPrivados;
const getDatoPrivado = (req, res) => __awaiter(void 0, void 0, void 0, function* () {
    const { id, uid } = req.params;
    yield dato_1.default.findById(id)
        .where("idUsuario")
        .equals(uid)
        .exec()
        .then((resultado) => {
        return res.status(200).json({
            exito: true,
            datos: resultado,
        });
    })
        .catch((error) => {
        return res.status(500).json({
            exito: false,
            error,
        });
    });
});
exports.getDatoPrivado = getDatoPrivado;
const postDatoPrivado = (req, res) => __awaiter(void 0, void 0, void 0, function* () {
    const { titulo, nota, items, idUsuario } = req.body;
    const datoPrivado = new dato_1.default({
        _id: new mongoose_1.default.Types.ObjectId(),
        titulo,
        nota,
        items,
        idUsuario,
    });
    yield datoPrivado
        .save()
        .then((resultado) => {
        return res.status(200).json({
            exito: true,
            datos: resultado,
        });
    })
        .catch((error) => {
        return res.status(500).json({
            exito: false,
            error,
        });
    });
});
exports.postDatoPrivado = postDatoPrivado;
const putDatoPrivado = (req, res) => __awaiter(void 0, void 0, void 0, function* () {
    const { id } = req.params;
    const { titulo, nota, items } = req.body;
    yield dato_1.default.findByIdAndUpdate({ _id: id }, { titulo, nota, items })
        .then((resultado) => {
        return res.status(200).json({
            exito: true,
        });
    })
        .catch((error) => {
        return res.status(500).json({
            exito: false,
            error,
        });
    });
});
exports.putDatoPrivado = putDatoPrivado;
const deleteDatoPrivado = (req, res) => __awaiter(void 0, void 0, void 0, function* () {
    const { id } = req.params;
    yield dato_1.default.findByIdAndDelete({ _id: id })
        .then((resultado) => {
        return res.status(200).json({
            exito: true,
            datos: resultado,
        });
    })
        .catch((error) => {
        return res.status(500).json({
            exito: false,
            error,
        });
    });
});
exports.deleteDatoPrivado = deleteDatoPrivado;
//# sourceMappingURL=dato.js.map