"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
const express_1 = require("express");
const dato_1 = require("../controllers/dato");
const router = (0, express_1.Router)();
//Establecemos los endpoints de cada solicitud
router.get("/:uid", dato_1.getDatosPrivados);
router.get("/:id/:uid", dato_1.getDatoPrivado);
router.post("/", dato_1.postDatoPrivado);
router.put("/:id", dato_1.putDatoPrivado);
router.delete("/:id", dato_1.deleteDatoPrivado);
exports.default = router;
//# sourceMappingURL=dato.js.map