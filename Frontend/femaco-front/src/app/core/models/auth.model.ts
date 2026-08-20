export interface LoginRequest {
    correoElectronico: string;
    password: string;
}

export interface LoginResponse {
    token: string;
    idUsuario: number;
    nombre: string;
}

export interface UserData {
    token: string;
    idUsuario: number;
    nombre: string;
}