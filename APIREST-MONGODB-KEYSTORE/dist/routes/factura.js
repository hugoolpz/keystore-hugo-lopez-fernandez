"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
const express_1 = require("express");
const factura_1 = require("../controllers/factura");
const router = (0, express_1.Router)();
//Establecemos los endpoints de cada solicitud
router.get("/", factura_1.getFacturas);
router.get("/:id", factura_1.getFactura);
router.post("/", factura_1.postFactura);
router.put("/:id", factura_1.putFactura);
router.delete("/:id", factura_1.deleteFactura);
exports.default = router;
//# sourceMappingURL=factura.js.map