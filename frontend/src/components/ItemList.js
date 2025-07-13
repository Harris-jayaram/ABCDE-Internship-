import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { toast, ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

const ItemList = ({ token }) => {
  const [items, setItems] = useState([]);
  const [cartId, setCartId] = useState(null);

  useEffect(() => {
    axios.get('http://localhost:8080/items').then((response) => {
      setItems(response.data);
    });
  }, []);

  const addToCart = async (itemId) => {
    try {
      const response = await axios.post(
        'http://localhost:8080/carts',
        { itemId },
        { headers: { Authorization: token } }
      );
      setCartId(response.data.id);
      toast.success('Item added to cart');
    } catch {
      toast.error('Failed to add item');
    }
  };

  const viewCart = async () => {
    try {
      const response = await axios.get('http://localhost:8080/carts', {
        headers: { Authorization: token },
      });
      const cartItems = response.data.map((cart) => ({
        cartId: cart.id,
        itemIds: cart.items.map((item) => item.id),
      }));
      toast.info(JSON.stringify(cartItems));
    } catch {
      toast.error('Failed to fetch cart');
    }
  };

  const viewOrders = async () => {
    try {
      const response = await axios.get('http://localhost:8080/orders', {
        headers: { Authorization: token },
      });
      const orderIds = response.data.map((order) => order.id);
      toast.info(`Your Orders: ${orderIds.join(', ')}`);
    } catch {
      toast.error('Failed to fetch orders');
    }
  };

  const checkout = async () => {
    try {
      await axios.post(
        'http://localhost:8080/orders',
        { cartId },
        { headers: { Authorization: token } }
      );
      toast.success('Order placed successfully!');
      setCartId(null);
    } catch {
      toast.error('Failed to place order');
    }
  };

  return (
    <div className="item-container">
      <div className="top-buttons">
        <button onClick={checkout} disabled={!cartId}>Checkout</button>
        <button onClick={viewCart}>View Cart</button>
        <button onClick={viewOrders}>Order History</button>
      </div>

      <h2>Available Items</h2>
      <ul className="item-list">
        {items.map((item) => (
          <li key={item.id} onClick={() => addToCart(item.id)}>
            <span className="item-name">{item.name}</span>
            <span className="item-desc">{item.description}</span>
          </li>
        ))}
      </ul>
      <ToastContainer position="bottom-right" />
    </div>
  );
};

export default ItemList;
