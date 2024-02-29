"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.ItemDatoKeyStore = void 0;
const mongoose_1 = require("mongoose");
exports.ItemDatoKeyStore = new mongoose_1.Schema({
    nombre: { type: String, required: true },
    contenido: { type: String, required: true },
    esWeb: { type: Boolean, required: true },
});
//# sourceMappingURL=itemDato.js.map