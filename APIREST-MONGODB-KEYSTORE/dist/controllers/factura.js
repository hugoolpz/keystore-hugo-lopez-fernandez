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
exports.deleteFactura = exports.putFactura = exports.postFactura = exports.getFactura = exports.getFacturas = void 0;
const factura_1 = __importDefault(require("../models/factura"));
const mongoose_1 = __importDefault(require("mongoose"));
const getFacturas = (req, res) => __awaiter(void 0, void 0, void 0, function* () {
    yield factura_1.default.find()
        .exec()
        .then((resultados) => {
        return res.status(200).json({
            exito: true,
            facturas: resultados,
            cantidad: resultados.length,
        });
    })
        .catch((error) => {
        return res.status(500).json({
            exito: false,
            error,
        });
    });
});
exports.getFacturas = getFacturas;
const getFactura = (req, res) => __awaiter(void 0, void 0, void 0, function* () {
    const { id } = req.params;
    yield factura_1.default.findById(id)
        .exec()
        .then((resultado) => {
        return res.status(200).json({
            exito: true,
            factura: resultado,
        });
    })
        .catch((error) => {
        return res.status(500).json({
            exito: false,
            error,
        });
    });
});
exports.getFactura = getFactura;
const postFactura = (req, res) => __awaiter(void 0, void 0, void 0, function* () {
    const { concepto } = req.body;
    const factura = new factura_1.default({
        _id: new mongoose_1.default.Types.ObjectId(),
        concepto,
    });
    yield factura
        .save()
        .then((resultado) => {
        return res.status(200).json({
            exito: true,
            factura: resultado,
        });
    })
        .catch((error) => {
        return res.status(500).json({
            exito: false,
            error,
        });
    });
});
exports.postFactura = postFactura;
const putFactura = (req, res) => __awaiter(void 0, void 0, void 0, function* () {
    const { id } = req.params;
    const { concepto } = req.body;
    yield factura_1.default.findByIdAndUpdate({ _id: id }, { concepto })
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
exports.putFactura = putFactura;
const deleteFactura = (req, res) => __awaiter(void 0, void 0, void 0, function* () {
    const { id } = req.params;
    yield factura_1.default.findByIdAndDelete({ _id: id })
        .then((resultado) => {
        return res.status(200).json({
            exito: true,
            factura: resultado,
        });
    })
        .catch((error) => {
        return res.status(500).json({
            exito: false,
            error,
        });
    });
});
exports.deleteFactura = deleteFactura;
//# sourceMappingURL=factura.js.map