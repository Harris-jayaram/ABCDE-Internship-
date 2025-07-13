import axios from "axios";

const API_BASE = "http://localhost:8080";

export const api = axios.create({
  baseURL: API_BASE
});

export const loginUser = (data) => api.post("/users/login", data);
export const fetchItems = () => api.get("/items");
export const addItemToCart = (itemId, token) => api.post("/carts", { itemId }, { headers: { Authorization: token } });
export const getCart = (token) => api.get("/carts", { headers: { Authorization: token } });
export const checkoutOrder = (cartId, token) => api.post("/orders", { cartId }, { headers: { Authorization: token } });
export const getOrders = (token) => api.get("/orders", { headers: { Authorization: token } });
