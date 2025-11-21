export interface UserModel {
    uuid: string;
    email: string;
    name: string;
    role: Roles;
    createdDate: String;
}

enum Roles{
    ADMIN, EDITOR, Viewer
}