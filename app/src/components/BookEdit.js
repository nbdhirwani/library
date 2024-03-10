import React, { useEffect, useState } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Container, Form, FormGroup, Input, Label } from 'reactstrap';
import AppNavbar from './AppNavbar';
import { createBook } from '../services/BookService.js'

const BookEdit = () => {
  const initialFormState = {
    name: '',
    authorName: ''
  };
  const [book, setBook] = useState(initialFormState);
  const navigate = useNavigate();
  const { id } = useParams();

  const handleChange = (event) => {
    const { name, value } = event.target
    setBook({ ...book, [name]: value })
  }

  const handleSubmit = async (event) => {
    event.preventDefault();
    await createBook(book);
    setBook(initialFormState);
    navigate('/books');
  }

  return (<div>
      <AppNavbar/>
      <Container>
        <h2>Add Book</h2>
        <Form onSubmit={handleSubmit}>
          <FormGroup>
            <Label for="name">Name</Label>
            <Input type="text" name="name" id="name" value={book.name || ''}
                   onChange={handleChange} autoComplete="name"/>
          </FormGroup>
          <FormGroup>
            <Label for="authorName">Author</Label>
            <Input type="text" name="authorName" id="authorName" value={book.authorName || ''}
                   onChange={handleChange} autoComplete="name"/>
          </FormGroup>
          <FormGroup>
            <Button color="primary" type="submit">Save</Button>{' '}
            <Button color="secondary" tag={Link} to="/books">Cancel</Button>
          </FormGroup>
        </Form>
      </Container>
    </div>
  )
};

export default BookEdit;