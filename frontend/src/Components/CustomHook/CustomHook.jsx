export function useGetToken() {
    const token = localStorage.getItem("jwtToken");
    return token;
}
