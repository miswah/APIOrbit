export interface ActivityLogsModel {
    userId: String;
    userName: String;
    timestamp: Date;
    clientIp: String;
    httpMethod: String;
    path: String;
    moduleName: String;
    actionName: String;
    outcome: String;
    duration: number;
    params: String;
    traceId: String;
}