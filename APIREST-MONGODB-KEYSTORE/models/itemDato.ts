import mongoose, { Schema } from "mongoose";
import ItfcItemDatoKeyStore from "../interfaces/itemDato";

export const ItemDatoKeyStore = new Schema<ItfcItemDatoKeyStore>({
  nombre: { type: String, required: true },
  contenido: { type: String, required: true },
  esWeb: { type: Boolean, required: true },
});
