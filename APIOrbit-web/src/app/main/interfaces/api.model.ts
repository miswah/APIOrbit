export interface APIModel {
    id: string;
    name: string;
    description: string;
    category: string;
    tags: string;
    urlBase: string;
    version: number;
    status: string;
    authType: string;
    documentationUrl: string;
    mockUrl: string;
    createdBy: string;
    approvedBy: string;
    instructions: string;
    updatedDate: Date;
    tagArray?: string[];
}

export interface MockApi {
    id: string;
    delay: number;
    schemaRequest: string;
    schemaResponse: string
}