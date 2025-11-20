import {ROLE} from "./role"

export interface User {
    id: String;
    email: string;
    name: string;
    role: ROLE;
    token?: string;
    }