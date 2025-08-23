// If API response comes they will come only in this format

class ApiResponse{
    constructor(
        statusCode,
        message = "Success",
        data
    ){
        this.statusCode = statusCode;
        this.data = data;
        this.message = message;
        this.statusCode = statusCode < 400 ;
    }
}

export { ApiResponse }