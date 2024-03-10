import { render, screen } from '@testing-library/react';
import App from './App';

jest.mock('./components/BookList', () => () => {
    return <div>BookList</div>
});

jest.mock('./components/BookEdit', () => () => {
    return <div>BookEdit</div>
});

describe('App', () => {
    beforeEach(() => {
        jest.clearAllMocks();
    });

    test('renders application landing page successfully', () => {
      render(<App />);
      expect(screen.getByText(/Home/i)).toBeInTheDocument();
      expect(screen.getByText(/GitHub/i)).toBeInTheDocument();
      expect(screen.getByText(/Manage Books/i)).toBeInTheDocument();
    });
});