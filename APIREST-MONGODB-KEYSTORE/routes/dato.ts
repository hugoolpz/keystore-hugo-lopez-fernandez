import { Router } from "express";
import {
  deleteDatoPrivado,
  getDatoPrivado,
  getDatosPrivados,
  postDatoPrivado,
  putDatoPrivado,
} from "../controllers/dato";

const router = Router();

//Establecemos los endpoints de cada solicitud
router.get("/:uid", getDatosPrivados);
router.get("/:id/:uid", getDatoPrivado);
router.post("/", postDatoPrivado);
router.put("/:id", putDatoPrivado);
router.delete("/:id", deleteDatoPrivado);

export default router;
