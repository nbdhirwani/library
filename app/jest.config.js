module.exports = {
    testEnvironment: 'jsdom',
    rootDir: '.',
    modulePaths: ['<rootDir>'],
    moduleDirectories: ['node_modules', 'src'],
    setupFilesAfterEnv: ['./setupJest.js'],
  moduleNameMapper: {
    '\\.(jpg|jpeg|png|gif|eot|otf|webp|svg|ttf|woff|woff2|mp4|webm|wav|mp3|m4a|aac|oga)$':
      '<rootDir>/__mocks__/fileMock.js',
    '\\.(css|less)$': '<rootDir>/__mocks__/styleMock.js',
    "^axios$": "axios/dist/node/axios.cjs"
  },
};