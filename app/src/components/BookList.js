import React, { useEffect, useState } from 'react';
import { Button, ButtonGroup, Container, Table, Modal, ModalHeader, ModalBody, ModalFooter, Dropdown, DropdownToggle, DropdownMenu, DropdownItem } from 'reactstrap';
import AppNavbar from './AppNavbar';
import { Link } from 'react-router-dom';
import { getAllBooks, deleteBook, updateBook } from '../services/BookService.js';

const BookList = () => {

  const [books, setBooks] = useState([]);
  const [loading, setLoading] = useState(false);
  const [modal, setModal] = useState(false);
  const [confirmationModal, setConfirmationModal] = useState(false);
  const [modalTitle, setModalTitle] = useState('');
  const [modalBody, setModalBody] = useState('');
  const [dropdownOpen, setDropdownOpen] = useState(false);
  const [sortField, setSortField] = useState('id');
  const [sortDirection, setSortDirection] = useState('ASC');
  const [bookToBeDeleted, setBookToBeDeleted] = useState('');


  const toggleModal = () => setModal(!modal)
  const toggleConfirmationModal = () => setConfirmationModal(!confirmationModal)
  const toggleDropDown = () => setDropdownOpen((prevState) => !prevState);

  const removeBook = (id) => {
    setBookToBeDeleted(id);
    setConfirmationModal(true);
  }

  const showModal = (title, body) => {
    setModalTitle(title);
    setModalBody(body);
    setModal(true);
  }

  useEffect(() => {
    setLoading(true);

    getAllBooks(sortField, sortDirection)
      .then(response => {
        setBooks(response.data);
        setLoading(false);
      });
  }, [sortField, sortDirection]);

  const sortBy = async (event) => {
    setLoading(true);
    const name = event.target.name;
    var field=sortField, direction=sortDirection
    switch(name) {
        case "nameAsc":
            field = "name";
            direction = "ASC";
            break;
        case "nameDesc":
            field = "name";
            direction = "DESC";
            break;
        case "authorNameAsc":
            field = "authorName";
            direction = "ASC";
            break;
        case "authorNameDesc":
            field = "authorName";
            direction = "DESC";
            break;
        default:
            field = "id";
            direction = "ASC";
            break;
    }
    setSortField(field);
    setSortDirection(direction);

    await getAllBooks(field, direction)
      .then(response => {
        setBooks(response.data);
        setLoading(false);
      });
  }

  const remove = async (id) => {
    await deleteBook(id).then(() => {
      let updatedBooks = [...books].filter(i => i.id !== id);
      setBooks(updatedBooks);
      toggleConfirmationModal();
    });
  }

  const toggle = async (id, borrowed) => {
    const payload = {isBorrowed: !borrowed}
    await updateBook(id, payload).then(() => {
      let updatedBooks = [...books].map(book => {
        if(book.id === id){
            return {...book, isBorrowed: !borrowed};
        } else {
            return {...book};
        }
      });
      setBooks(updatedBooks);
      showModal("Action", payload.isBorrowed ? "Book borrowed" : "Book returned");
    });
  }

  if (loading) {
    return <p>Loading...</p>;
  }

  const BookList = books.map(book => {
    return <tr key={book.id}>
      <td style={{whiteSpace: 'nowrap'}}>{book.name}</td>
      <td>{book.authorName}</td>
      <td>{book.isBorrowed ? "Borrowed" : "Available"}</td>
      <td>
        <ButtonGroup>
          <Button size="sm" color={book.isBorrowed ? 'secondary' : 'primary'}onClick={() => toggle(book.id, book.isBorrowed)}>{book.isBorrowed ? 'Return' : 'Borrow'}</Button>
          <Button size="sm" color="danger" onClick={() => removeBook(book.id)}>Delete</Button>
        </ButtonGroup>
      </td>
    </tr>
  });

  return (
    <div>
      <AppNavbar/>
      <Container fluid>
        <Modal isOpen={modal} toggle={toggleModal}>
          <ModalHeader toggle={toggleModal}>{modalTitle}</ModalHeader>
          <ModalBody>{modalBody}</ModalBody>
          <ModalFooter>
            <Button color="primary" onClick={toggleModal}>Close</Button>
          </ModalFooter>
        </Modal>

        <Modal isOpen={confirmationModal} toggle={toggleConfirmationModal}>
          <ModalHeader toggle={toggleConfirmationModal}>Confirmation</ModalHeader>
          <ModalBody>Are you sure that you want to delete this book?</ModalBody>
          <ModalFooter>
            <Button color="danger" onClick={() => remove(bookToBeDeleted)}>Yes</Button>
            <Button color="success" onClick={toggleConfirmationModal}>No</Button>
          </ModalFooter>
        </Modal>

        <div className="float-end d-flex p5">
          <Button className="me-2" color="success" tag={Link} to="/books/new">Add Book</Button>
          <Dropdown className="me-2" isOpen={dropdownOpen} toggle={toggleDropDown} direction="down">
            <DropdownToggle caret color="success">Sort By</DropdownToggle>
            <DropdownMenu>
                <DropdownItem name="default" onClick={sortBy}>Default</DropdownItem>
                <DropdownItem name="nameAsc" onClick={sortBy}>Name Ascending</DropdownItem>
                <DropdownItem name="nameDesc" onClick={sortBy}>Name Descending</DropdownItem>
                <DropdownItem name="authorNameAsc" onClick={sortBy}>Author Name Ascending</DropdownItem>
                <DropdownItem name="authorNameDesc" onClick={sortBy}>Author Name Descending</DropdownItem>
            </DropdownMenu>
          </Dropdown>
        </div>
        <h3>All Books</h3>
        <Table className="mt-4" striped responsive>
          <thead>
              <tr>
                <th width="20%">Name</th>
                <th width="20%">Author</th>
                <th width="20%">Status</th>
                <th width="10%">Actions</th>
              </tr>
          </thead>
          <tbody>{BookList}</tbody>
        </Table>
      </Container>
    </div>
  );
};

export default BookList;