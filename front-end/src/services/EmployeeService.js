import axios from "axios";

const REST_API_BASE_URL = 'http://127.0.0.1:8080/api/employees';
export const listEmployees = () => axios.get(REST_API_BASE_URL);
export const createEmployee = (employee) => axios.post(REST_API_BASE_URL, employee);
// export const uploadImage = (id, formData) => axios.post(`${REST_API_BASE_URL}/${id}/uploadImage`, formData);
export const uploadImage = (id, imageFile) => {
    let formData = new FormData();
    formData.append("file", imageFile);
    return axios.post(`${REST_API_BASE_URL}/${id}/uploadImage`, formData, {
        headers: {
            'Content-Type': 'multipart/form-data'
        }
    });
};
export const getImage = (id) => axios.get(`${REST_API_BASE_URL}/${id}/image`);