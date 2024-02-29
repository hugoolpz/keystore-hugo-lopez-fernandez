import { Request, Response } from "express";
import DatoPrivado from "../models/dato";
import mongoose from "mongoose";

const getDatosPrivados = async (req: Request, res: Response) => {
  const { uid } = req.params;

  await DatoPrivado.find()
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
};

const getDatoPrivado = async (req: Request, res: Response) => {
  const { id, uid } = req.params;

  await DatoPrivado.findById(id)
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
};

const postDatoPrivado = async (req: Request, res: Response) => {
  const { titulo, nota, items, idUsuario } = req.body;

  const datoPrivado = new DatoPrivado({
    _id: new mongoose.Types.ObjectId(),
    titulo,
    nota,
    items,
    idUsuario,
  });

  await datoPrivado
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
};

const putDatoPrivado = async (req: Request, res: Response) => {
  const { id } = req.params;
  const { titulo, nota, items } = req.body;

  await DatoPrivado.findByIdAndUpdate({ _id: id }, { titulo, nota, items })
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
};

const deleteDatoPrivado = async (req: Request, res: Response) => {
  const { id } = req.params;

  await DatoPrivado.findByIdAndDelete({ _id: id })
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
};

export {
  getDatosPrivados,
  getDatoPrivado,
  postDatoPrivado,
  putDatoPrivado,
  deleteDatoPrivado,
};
