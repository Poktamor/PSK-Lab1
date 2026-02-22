import {Table, Col, Row, Nav, Tabs, Tab} from 'react-bootstrap'
import {useEffect, useState} from "react";
import './App.css'
import SubmarinesModal from "./SubmarinesModal.jsx";

// let didInit = false;

function Submarines() {
    const [tableContent, setTableContent] = useState([]);
    const [selectedSubmarine, setSelectedSubmarine] = useState({});
    const [showModal, setShowModal] = useState(false);

    const fetchTable = async () => {
        try {
            const response = await fetch('http://localhost:8080/api/Submarines');
            setTableContent(await response.json());
        } catch (e) {
            console.error(e);
        }
    }
    useEffect(() => {
        // eslint-disable-next-line react-hooks/set-state-in-effect
        fetchTable();
    }, []);

    const tableHead = (
        <tr>
            <th>Id</th>
            <th>Name</th>
        </tr>
    )

    const handleRowClick = (submarine) => {
        setSelectedSubmarine(submarine);
        setShowModal(true);
    }

    const tableBody = tableContent.map(item =>
        <tr key={item.id} onClick={() => {handleRowClick(item)} }>
            <td>{item.id}</td>
            <td>{item.name}</td>
        </tr>
    )

    return (
        <main className="w-100">
            <Table striped hover className="selectableTable">
                <thead>
                    {tableHead}
                </thead>
                <tbody>
                    {tableBody}
                </tbody>
            </Table>
            <SubmarinesModal selectedSubmarine={selectedSubmarine} setSelectedSubmarine={setSelectedSubmarine} showModal={showModal} setShowModal={setShowModal} fetchTable={fetchTable}/>
        </main>
    )
}

export default Submarines
