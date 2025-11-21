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