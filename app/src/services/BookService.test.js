import api from './api';
import { getAllBooks, getOneBook, deleteBook, createBook, updateBook } from './BookService';

jest.mock("./api");

describe('BookService', () => {
    beforeEach(() => {
        jest.clearAllMocks();
    });

    test('verify get all', async () => {
        let mockFunction = jest.fn();
        api.get = mockFunction.mockResolvedValue({data: {}});
        let result = await getAllBooks("id", "ASC");
        expect(result).toEqual({data: {}});
        expect(mockFunction).toHaveBeenCalled();
    });

    test('verify get one', async () => {
        let mockFunction = jest.fn();
        api.get = mockFunction.mockResolvedValue({data: {}});
        let result = await getOneBook("id");
        expect(result).toEqual({data: {}});
        expect(mockFunction).toHaveBeenCalled();
    });

    test('verify delete', async () => {
        let mockFunction = jest.fn();
        api.delete = mockFunction;
        await deleteBook("id");
        expect(mockFunction).toHaveBeenCalled();
    });

    test('verify update', async () => {
        let mockFunction = jest.fn();
        api.put = mockFunction;
        await updateBook("id", {});
        expect(mockFunction).toHaveBeenCalled();
    });

    test('verify create', async () => {
        let mockFunction = jest.fn();
        api.post = mockFunction;
        await createBook({});
        expect(mockFunction).toHaveBeenCalled();
    });
});