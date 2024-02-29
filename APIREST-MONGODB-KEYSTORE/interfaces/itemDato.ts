import { Document } from "mongoose";

export default interface ItfcItemDatoKeyStore extends Document {
  nombre: string;
  contenido: string;
  esWeb: boolean;
}
