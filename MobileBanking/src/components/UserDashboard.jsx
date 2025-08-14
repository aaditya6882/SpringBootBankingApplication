import {Balance} from "./Balance.jsx";
import {Transaction} from "./Transaction.jsx";
export default function UserDashboard() {
        return (
            <div style={{ padding: "50px" }}>
                <h2>User Dashboard</h2>
                <Balance />
                <Transaction />
            </div>
        );
}
