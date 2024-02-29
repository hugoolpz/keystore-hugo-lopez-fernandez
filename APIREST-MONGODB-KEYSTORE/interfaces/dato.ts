import { Document } from "mongoose";

export default interface ItfcDatoKeyStore extends Document {
  titulo: string;
  nota: string;
  items: string[];
  idUsuario: string;
}
