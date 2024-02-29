import mongoose, { Schema } from "mongoose";
import ItfcDatoKeyStore from "../interfaces/dato";
import { ItemDatoKeyStore } from "./itemDato";

const SchemaDatoKeyStore: Schema = new Schema<ItfcDatoKeyStore>(
  {
    titulo: { type: String, required: true },
    nota: { type: String, required: true },
    items: [ItemDatoKeyStore],
    idUsuario: { type: String, required: true },
  },
  { timestamps: false, versionKey: false }
);

export default mongoose.model("datos_privados", SchemaDatoKeyStore);
