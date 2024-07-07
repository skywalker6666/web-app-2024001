import axios from "axios";

const REST_API_BASE_URL = 'http://127.0.0.1:8080/api/employees';
const IMAGE_UPLOAD_URL='http://127.0.0.1:8080/api/image/fileSystem';
export const listEmployees = () => axios.get(REST_API_BASE_URL);
export const createEmployee=(employee)=>axios.post(REST_API_BASE_URL,employee);
export const uploadImage=(image)=>axios.post(IMAGE_UPLOAD_URL,image);
export const getImage=(image)=>axios.get(IMAGE_UPLOAD_URL,image);