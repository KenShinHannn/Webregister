const localhost = "http://localhost:8080"
const getUser = async()=>{
    try {
        const res = await fetch(`${localhost}/api/users`,{
            method : "GET",
            headers :{
                "Authorization": `Bearer ${localStorage.getItem("token")}`,
            }
        })
        if(res.status === 200){
            return await res.json()
        }
    } catch (error) {
        console.log(`Error : ${error}`);
    }
}

export {getUser}